package com.bzmyslowski.fullstackwchmurze;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class ServerInfoController {

    /**
     * Metoda kontrollera pobierająca adres IP klienta i przekazująca go do szablonu Thymeleafa.
     */
    @GetMapping("/")
    public String displayServerInfo(HttpServletRequest request, Model model) {
        //pobranie adresu IP klienta
        String ipAddress = request.getRemoteAddr();
        String ipv4Address;

        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            ipv4Address = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            //Obsługa wyjątku - wyświetlenie "Unknown" w polu adresu
            ipv4Address = "Unknown";
        }

        //dodanie adresu IP do modelu zwracanego w szablonie HTML
        model.addAttribute("ipAddress", ipv4Address);

        //zwrócenie widoku
        return "server-info";
    }

}
