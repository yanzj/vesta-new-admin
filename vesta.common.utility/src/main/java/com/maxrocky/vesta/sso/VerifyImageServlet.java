package com.maxrocky.vesta.sso;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyImageServlet extends HttpServlet {
    public VerifyImageServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        byte width = 68;
        byte height = 24;
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(new Color(244, 244, 244));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Arial", 2, 24));

        int encoder;
        for(int sRand = 0; sRand < 155; ++sRand) {
            encoder = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(encoder, y, encoder + xl, y + yl);
        }

        String var14 = this.setRandValue(request);
        if(var14 != null) {
            for(encoder = 0; encoder < var14.length(); ++encoder) {
                g.setColor(new Color(0, 0, 0));
                g.drawString(String.valueOf(var14.charAt(encoder)), 16 * encoder + 4, 20);
            }
        }

        ImageIO.write(image, " JPEG ", out);
//        JPEGImageEncoder var15 = JPEGCodec.createJPEGEncoder(out);
//        var15.encode(image);
        g.dispose();
        out.flush();
        out.close();
    }

    public Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255) {
            fc = 255;
        }

        if(bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public String setRandValue(HttpServletRequest request) {
        Random random = new Random();
        String sRand = "";

        for(int i = 0; i < 4; ++i) {
            boolean c = false;
            char var6 = (char)(random.nextInt(10) + 48);
            sRand = sRand + var6;
        }

        request.getSession().setAttribute("sso_rands", sRand);
        return sRand;
    }
}
