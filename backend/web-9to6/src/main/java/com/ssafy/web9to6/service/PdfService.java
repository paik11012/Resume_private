package com.ssafy.web9to6.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.ssafy.web9to6.domain.Resume;
import com.ssafy.web9to6.domain.Users;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileOutputStream;


@Service
public class PdfService {
    public String  createPdf(Resume resume) {
        String result = "";
        try {
            Document document = new Document(); // pdf문서를 처리하는 객체
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("file/" +resume.getId().toString()+".pdf"));
            document.open(); // 웹페이지에 접근하는 객체를 연다
            File file2 = ResourceUtils.getFile("malgun.ttf");
            //pdf가 기본적으로 한글처리가 안되기 때문에 한글폰트 처리를 따로 해주어야 한다.
            // createFont메소드에 사용할 폰트의 경로 (malgun.ttf)파일의 경로를 지정해준다.
            BaseFont baseFont = BaseFont.createFont(String.valueOf(file2), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font fontTitle = new Font(baseFont, 17, Font.BOLD);
            Font fontContent = new Font(baseFont, 12);

            Chunk chunk = new Chunk(resume.getResume_company() + "_" + resume.getResume_task() + "_" + resume.getResume_date(), fontTitle);
            Paragraph ph = new Paragraph(chunk);
            ph.setAlignment(Element.ALIGN_CENTER);
            document.add(ph); // 문단을 만들어서 가운데 정렬 (타이틀의 이름을 가운데 정렬한다는 뜻)

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE); // 줄바꿈 (왜냐하면 타이틀에서 두줄을 내린후에 셀(테이블)이 나오기 때문)

            Chunk chunk2 = new Chunk(" [ " + resume.getResume_question() + " ] ", fontContent);
            Paragraph ph2 = new Paragraph(chunk2);
            ph.setAlignment(Element.ALIGN_LEFT);
            document.add(ph2);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Chunk chunk3 = new Chunk(resume.getResume_answer(), fontContent); // 타이틀 객체를 생성 (타이틀의 이름을 장바구니로 하고 위에 있는 font를 사용)
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
