package com.ssafy.web9to6.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.web9to6.domain.Awards;
import com.ssafy.web9to6.domain.Interview;
import com.ssafy.web9to6.domain.Resume;
import com.ssafy.web9to6.dto.AwardsResponseDto;
import com.ssafy.web9to6.dto.InterviewResponseDto;
import com.ssafy.web9to6.dto.ResumeResponseDto;
import com.ssafy.web9to6.dto.TagResponseDto;
import com.ssafy.web9to6.service.*;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.apache.catalina.User;
import org.apache.catalina.connector.Response;
import org.hibernate.type.MapType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @ApiOperation("자소서 조회")
    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    public List<Resume> findAllResume(HttpSession session) {
        String user_id = (String)session.getAttribute("user_id");
        return rs.findAll(us.findById(user_id));
    }

//    @ApiOperation("자소서 저장")
//    @RequestMapping(value = "/resume/save", method = RequestMethod.POST)
//    public Resume saveResume(HttpSession session,@RequestBody ResumeResponseDto rrd) {
//   //     String user_id = (String)session.getAttribute("user_id");
//     //   rrd.setUser(us.findById(user_id));
//        rrd.setUser(us.findById("temp"));
//        return rs.save(rrd);
//    }

        @ApiOperation("자소서 저장")
    @RequestMapping(value = "/resume/save", method = RequestMethod.POST)
    public void saveResume(HttpSession session,@RequestBody Map<String,Object> rrd) throws Exception {
            Map res  = (Map)rrd.get("resume_info");
            ArrayList<String> tags = (ArrayList<String>) rrd.get("tag_name");
            System.out.println(rrd.get("tag_name").getClass());
            ResumeResponseDto resume = new ResumeResponseDto();
            resume.setResume_answer((String)res.get("resume_answer"));
            resume.setResume_date((String)res.get("resume_task"));
            resume.setResume_company((String)res.get("resume_company"));
            resume.setResume_question((String)res.get("resume_question"));
            resume.setResume_task((String)res.get("resume_task"));
            resume.setUser(us.findById("test"));

            try {
                Resume r =  rs.save(resume);
                Long id = r.getId();
                System.out.print("Dd" + tags.size());
                System.out.print("Dd" + ((ArrayList<String>) rrd.get("tag_name")).get(0).getClass());
                System.out.print("Dd" + ((ArrayList<String>) rrd.get("tag_name")).get(0));

                for(int i=0;i<tags.size();i++) {
                    TagResponseDto trd = new TagResponseDto();
                    trd.setResume(r);
                    trd.setTag_name(tags.get(i));
                    ts.save(trd);
                }
            } catch (Exception e) {
                throw new Exception();
            }






    }
    @ApiOperation("자소서 삭제")
    @RequestMapping(value = "/resume/del/{id}", method = RequestMethod.DELETE)
    public void deleteResume(HttpSession session, @PathVariable String id) {
        Long aid = Long.parseLong(id);
        rs.delete(aid);
    }

    @ApiOperation("자소서 수정")
    @RequestMapping(value = "/resume/update", method = RequestMethod.PUT)
    public Resume updateResume(HttpSession session, @RequestBody ResumeResponseDto rrd) {
        String user_id = (String)session.getAttribute("user_id");
        rrd.setUser(us.findById(user_id));
        return rs.save(rrd);
    }

    @ApiOperation("면접 조회")
    @RequestMapping(value = "/interview", method = RequestMethod.GET)
    public List<Interview> findAllInterview(HttpSession session) {
        String user_id = (String)session.getAttribute("user_id");
        return is.findAll(us.findById(user_id));
    }

    @ApiOperation("면접 저장")
    @RequestMapping(value = "/interview/save", method = RequestMethod.POST)
    public Interview saveInterview(HttpSession session,@RequestBody InterviewResponseDto rrd) {
       // String user_id = (String)session.getAttribute("user_id");
        // rrd.setUser(us.findById(user_id));
        rrd.setUser(us.findById("temp"));
        return is.save(rrd);
    }

    @ApiOperation("면접 삭제")
    @RequestMapping(value = "/interview/del/{id}", method = RequestMethod.DELETE)
    public void deleteInterview(HttpSession session, @PathVariable String id) {
        Long aid = Long.parseLong(id);
        rs.delete(aid);
    }

    @ApiOperation("면접 수정")
    @RequestMapping(value = "/interview/update", method = RequestMethod.PUT)
    public Interview updateInterview(HttpSession session, @RequestBody InterviewResponseDto rrd) {
        String user_id = (String)session.getAttribute("user_id");
        rrd.setUser(us.findById(user_id));
        return is.save(rrd);
    }
}
