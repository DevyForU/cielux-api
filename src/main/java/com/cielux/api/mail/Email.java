package com.cielux.api.mail;

import com.cielux.api.PojaGenerated;
import jakarta.mail.internet.InternetAddress;

import java.io.File;
import java.util.List;

@PojaGenerated
public record Email(
    InternetAddress to,
    List<InternetAddress> cc,
    List<InternetAddress> bcc,
    String subject,
    String htmlBody,
    List<File> attachments) {}
