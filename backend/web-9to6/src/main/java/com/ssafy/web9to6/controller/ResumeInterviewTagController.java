package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.*;
import com.ssafy.web9to6.dto.*;
import com.ssafy.web9to6.service.*;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.apache.catalina.User;
import org.apache.catalina.connector.Response;
import org.hibernate.type.MapType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.Tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController

public class ResumeInterviewTagController {
    @Autowired
    private InterviewService is;
    @Autowired
    private TagService ts;
    @Autowired
    private ResumeService rs;
    @Autowired
    private UsersService us;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PdfService pdfService;

    @ApiOperation("자소서와 태그 조회")
    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    public List<ResumeTagResponseDto> findAllResumeWithTags(HttpServletRequest request) {
        String user_id =  request.getHeader("user_id");
        List<ResumeTagResponseDto> map = new ArrayList<>();
        List<Resume> list = rs.findAll(us.findById(user_id));

        for(int i=0;i<list.size();i++) {
            Resume resume = list.get(i);
            List<String> tags = ts.findAll(resume);
            System.out.println(tags.size());
            ResumeTagResponseDto rtrd = new ResumeTagResponseDto(resume,tags);
            System.out.println(rtrd.toString());
            map.add(rtrd);
        }
        return map;
    }

    @ApiOperation("태그별 자소서 조회")
    @RequestMapping(value = "/resume/{keyword}", method = RequestMethod.GET)
    public List<ResumeTagResponseDto> findAllResumeByTags(HttpServletRequest request, @PathVariable String keyword) {
        String user_id =  request.getHeader("user_id");
        List<ResumeTagResponseDto> map = new ArrayList<>();
        List<Resume> list = rs.findAll(us.findById(user_id));

        label : for(int i=0;i<list.size();i++) {
            Resume resume = list.get(i);
            List<String> tags = ts.findAll(resume);

            for(int j=0;j<tags.size();j++) {
                if(tags.get(j).equals(keyword)) {
                    ResumeTagResponseDto rtrd = new ResumeTagResponseDto(resume,tags);
                    map.add(rtrd);
                    continue label;
                }
            }
        }
        return map;
    }

    @ApiOperation("자소서 저장")
    @RequestMapping(value = "/resume/save", method = RequestMethod.POST)
    public void saveResume(HttpServletRequest request, @RequestBody Map<String,Object> rrd) throws Exception {
        System.out.println("req"  + request.getHeader("user_id"));
        Map res  = (Map)rrd.get("resume_info");
        ArrayList<String> tags = (ArrayList<String>) rrd.get("tag_name");
        String user_id =  request.getHeader("user_id");
        ResumeResponseDto resume = new ResumeResponseDto();
        resume.setResume_answer((String)res.get("resume_answer"));
        resume.setResume_date((String)res.get("resume_date"));
        resume.setResume_company((String)res.get("resume_company"));
        resume.setResume_question((String)res.get("resume_question"));
        resume.setResume_task((String)res.get("resume_task"));
        resume.setResume_pass((Boolean) res.get("resume_pass"));
        resume.setUser(us.findById(user_id));

        try {
            Resume r =  rs.save(resume);
            Long id = r.getId();
            for(int i=0;i<tags.size();i++) {
                TagResponseDto trd = new TagResponseDto();
                trd.setResume(r);
                trd.setTag_name(tags.get(i));
                ts.save(trd);
            }
        } catch (Exception e) {
            throw  new Exception();
        }
    }
    @ApiOperation("자소서 삭제")
    @RequestMapping(value = "/resume/del/{id}", method = RequestMethod.DELETE)
    public void deleteResume(@PathVariable String id) {
        Long aid = Long.parseLong(id);
        rs.delete(aid);
    }

    @ApiOperation("자소서 수정")
    @RequestMapping(value = "/resume/update", method = RequestMethod.POST)
    public void updateResume(HttpServletRequest request, @RequestBody Map<String,Object> rrd) {
        String user_id =  request.getHeader("user_id");
        Map res  = (Map)rrd.get("resume_info");
        ArrayList<String> tags = (ArrayList<String>) rrd.get("tag_name");
        ResumeResponseDto resume = new ResumeResponseDto();
        resume.setResume_answer((String)res.get("resume_answer"));
        resume.setResume_date((String)res.get("resume_date"));
        resume.setResume_company((String)res.get("resume_company"));
        resume.setResume_question((String)res.get("resume_question"));
        resume.setResume_task((String)res.get("resume_task"));
        resume.setUser(us.findById(user_id));
        resume.setResume_pass((Boolean) res.get("resume_pass"));
        resume.setId(Long.parseLong((String)res.get("id")));

        Resume find = rs.findById(resume.getId());
        Resume update = rs.update(find,resume.toEntity());

        List<Tag> list = ts.findAllTag(update);
        for(int i=0;i<list.size();i++) {
            ts.delete(list.get(i).getId());
        }

        for(int i=0;i<tags.size();i++) {
            TagResponseDto trd = new TagResponseDto();
            trd.setResume(update);
            trd.setTag_name(tags.get(i));
            ts.save(trd);
        }
    }

    @ApiOperation("면접 조회")
    @RequestMapping(value = "/interview", method = RequestMethod.GET)
    public List<Interview> findAllInterview(HttpServletRequest request) {
        String user_id =  request.getHeader("user_id");
        return is.findAll(us.findById(user_id));
    }


    @ApiOperation("면접 저장")
    @RequestMapping(value = "/interview/save", method = RequestMethod.POST)
    public Interview saveInterview(HttpServletRequest request,@RequestBody InterviewResponseDto rrd) {
        String user_id =  request.getHeader("user_id");
        rrd.setUser(us.findById(user_id ));
        return is.save(rrd);
    }

    @ApiOperation("면접 삭제")
    @RequestMapping(value = "/interview/del/{id}", method = RequestMethod.DELETE)
    public void deleteInterview(@PathVariable String id) {
        Long aid = Long.parseLong(id);
        is.delete(aid);
    }

    @ApiOperation("면접 수정")
    @RequestMapping(value = "/interview/update", method = RequestMethod.POST)
    public Interview updateInterview(HttpServletRequest request, @RequestBody InterviewResponseDto rrd) {
        String user_id =  request.getHeader("user_id");
        rrd.setUser(us.findById(user_id));
        return is.save(rrd);
    }

    @ApiOperation("면접 수정")
    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public void get() {
//      PdfService ser = new PdfService();

//      ser.createPdf();
    }


    @ApiOperation("자소서/이력서 갯수")
    @GetMapping("/interview/getLength")
    public Map<String, Integer> getLength(HttpServletRequest request){
        Map<String, Integer> map = new HashMap<>();
        String user_id = request.getHeader("user_id");
        map.put("n_resume", rs.findAll(us.findById(user_id)).size());
        map.put("n_interview", is.findAll(us.findById(user_id)).size());
        return map;
    }



    @ApiOperation("자기소개서 pdf 내보내기")
    @GetMapping("/attach/{resume_id}")
    public void attach(@PathVariable String resume_id, HttpServletRequest request) throws Exception {
        Resume resume = rs.findById(Long.parseLong(resume_id));
        Users user = us.findById(request.getHeader("user_id"));
        emailService.sendPdf(resume, user);
    }
}
