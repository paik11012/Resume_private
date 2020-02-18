package com.ssafy.web9to6.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ssafy.web9to6.domain.Resume;
import com.ssafy.web9to6.domain.Users;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

@Service
public class PdfService {
    public String  createPdf(Resume resume, Users user) {
        String result = ""; // 초기값이 null이 들어가면 오류가 발생될수 있기 때문에 공백을 지정

        try {
            Document document = new Document(); // pdf문서를 처리하는 객체
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("file/" +resume.getId().toString()+".pdf"));
            document.open(); // 웹페이지에 접근하는 객체를 연다
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("classpath:pdf/33"));
//            URL s = ResourceUtils.getURL("classpath:pdf");
//            System.out.println(s);
        //    ByteArrayOutputStream out = new ByteArrayOutputStream();
          //  ClassPathResource resource = new ClassPathResource("./resources/new.pdf");
          //  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(resource.getPath()));
            // pdf파일의 저장경로를 d드라이브의 sample.pdf로 한다는 뜻

//            File file2 = ResourceUtils.getFile("classpath:malgun.ttf");
            File file2 = ResourceUtils.getFile("malgun.ttf");
            System.out.println(file2.getAbsolutePath());
            BaseFont baseFont = BaseFont.createFont(String.valueOf(file2), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            // pdf가 기본적으로 한글처리가 안되기 때문에 한글폰트 처리를 따로 해주어야 한다.
            // createFont메소드에 사용할 폰트의 경로 (malgun.ttf)파일의 경로를 지정해준다.
            // 만약에 이 경로에 없을 경우엔 java파일로 만들어서 집어넣어야 한다.

            Font font = new Font(baseFont, 15); // 폰트의 사이즈를 12픽셀로 한다
            Font font2 = new Font(baseFont, 12); // 폰트의 사이즈를 12픽셀로 한다

            Chunk chunk = new Chunk("자기소개서", font); // 타이틀 객체를 생성 (타이틀의 이름을 장바구니로 하고 위에 있는 font를 사용)
            Paragraph ph = new Paragraph(chunk);
            ph.setAlignment(Element.ALIGN_CENTER);
            document.add(ph); // 문단을 만들어서 가운데 정렬 (타이틀의 이름을 가운데 정렬한다는 뜻)

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE); // 줄바꿈 (왜냐하면 타이틀에서 두줄을 내린후에 셀(테이블)이 나오기 때문)

            Chunk chunk2 = new Chunk(resume.getResume_question(), font2); // 타이틀 객체를 생성 (타이틀의 이름을 장바구니로 하고 위에 있는 font를 사용)
            Paragraph ph2 = new Paragraph(chunk2);
            ph.setAlignment(Element.ALIGN_LEFT);
            document.add(ph2); // 문단을 만들어서 가운데 정렬 (타이틀의 이름을 가운데 정렬한다는 뜻)

            Chunk chunk3 = new Chunk(resume.getResume_answer(), font2); // 타이틀 객체를 생성 (타이틀의 이름을 장바구니로 하고 위에 있는 font를 사용)
            Paragraph ph3 = new Paragraph(chunk3);
            ph.setAlignment(Element.ALIGN_LEFT);
            document.add(ph3); // 문단을 만들어서 가운데 정렬 (타이틀의 이름을 가운데 정렬한다는 뜻)

            document.close(); // 저장이 끝났으면 document객체를 닫는다.
            result = "pdf 파일 생성 성공";


        } catch (Exception e) {
            e.printStackTrace();
            result = "pdf 파일 생성 실패...";
        }

        return result;
    }


}
