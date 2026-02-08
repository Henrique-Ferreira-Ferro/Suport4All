package com.io.Suport4All.service;

import com.io.Suport4All.dto.NetworkCommandRequest;
import com.io.Suport4All.enums.NetworkCommandType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class NetworkService {

    public String execute(NetworkCommandRequest request) {

        validate(request);

        return switch (request.getCommand()) {
            case PING -> executePing(request);
            case TRACEROUTE -> executeTraceroute(request);
        };
    }

    private void validate(NetworkCommandRequest request) {

        if (request == null) {
            throw new RuntimeException("Request não pode ser nulo");
        }

        if (request.getCommand() == null) {
            throw new RuntimeException("Comando obrigatório");
        }

        if (!isValidHost(request.getHost())) {
            throw new RuntimeException("Host inválido");
        }
    }

    private boolean isValidHost(String host) {
        return host != null && host.length() < 255;
    }



    private String executePing(NetworkCommandRequest request) {
        String cmd;

        if (isWindows()) {
            cmd = "ping " + request.getHost();
            return executeShell(List.of("cmd.exe", "/c", cmd));
        } else {
            cmd = "ping -c " + request.getCount() + " " + request.getHost();
            return executeShell(List.of("/bin/bash", "-c", cmd));
        }
    }

    private String executeTraceroute(NetworkCommandRequest request) {
        List<String> command;

        if (isWindows()) {
            command = List.of("tracert", "-h", "15", request.getHost());
        } else {
            command = List.of("traceroute", request.getHost());
        }

        return executeCommand(command,10000);
    }
    private String executeShell(List<String> command) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process p = pb.start();

            String output = new String(p.getInputStream().readAllBytes());

            if (output.length() > 15000) {
                output = output.substring(0, 15000) + "\n[TRUNCADO]";
            }

            return output;

        } catch (IOException e) {
            return "[ERRO] " + e.getMessage();
        }
    }


    private String executeCommand(List<String> command, int timeoutSeconds) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            boolean finished = process.waitFor(timeoutSeconds, TimeUnit.SECONDS);
            if (!finished) {
                process.destroyForcibly();
                return "[ERRO] Timeout ao executar comando";
            }

            String output = new String(process.getInputStream().readAllBytes());

            if (output.length() > 15000) {
                output = output.substring(0, 15000) + "\n[TRUNCADO]";
            }

            return output;

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            return "[ERRO] Falha ao executar comando: " + e.getMessage();
        }
    }


    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}


