package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.Awards;
import com.ssafy.web9to6.domain.Experiences;
import com.ssafy.web9to6.domain.Licenses;
import com.ssafy.web9to6.dto.AwardsResponseDto;
import com.ssafy.web9to6.dto.ExperiencesResponseDto;
import com.ssafy.web9to6.dto.LicensesResponseDto;
import com.ssafy.web9to6.service.AwardsService;
import com.ssafy.web9to6.service.ExperiencesService;
import com.ssafy.web9to6.service.LicensesService;
import com.ssafy.web9to6.service.UsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ExperiencesController {
    @Autowired
    private AwardsService as;
    @Autowired
    private UsersService us;
    @Autowired
    private ExperiencesService ex;
    @Autowired
    private LicensesService ls;

    @ApiOperation("수상내역 조회")
    @RequestMapping(value = "/awards", method = RequestMethod.GET)
    public List<Awards> findAllAwards(HttpServletRequest request) {
        String user_id =  request.getHeader("user_id");
        return as.findAll(us.findById(user_id));
    }

    @ApiOperation("수상 정보 저장")
    @RequestMapping(value = "/awards/save", method = RequestMethod.POST)
    public Awards saveAwards(HttpServletRequest request,@RequestBody AwardsResponseDto awd) {
        String user_id =  request.getHeader("user_id");
        awd.setUser(us.findById(user_id));
        return as.save(awd);
    }

    @ApiOperation("수상정보 1개 삭제")
    @RequestMapping(value = "/awards/del/{id}", method = RequestMethod.DELETE)
    public void deleteAwards(@PathVariable String id) {
        Long aid = Long.parseLong(id);
        as.delete(aid);
    }

    @ApiOperation("수상 정보 수정")
    @RequestMapping(value = "/awards/update", method = RequestMethod.POST)
    public Awards updateAwards(HttpServletRequest request, @RequestBody AwardsResponseDto awd) {
        String user_id =  request.getHeader("user_id");
        awd.setUser(us.findById(user_id));
        Optional<Awards> old = as.findById(awd.getId());
        if(!old.isPresent()){
            return as.save(awd);
        } else return as.update(old.get(), awd);
    }

    @ApiOperation("경험 내역 전체 조회")
    @RequestMapping(value = "/exp", method = RequestMethod.GET)
    public List<Experiences> findAllExperiences(HttpServletRequest request) {
        String user_id =  request.getHeader("user_id");
        return ex.findAll(us.findById(user_id));
    }

    @ApiOperation("경험 정보 저장")
    @RequestMapping(value = "/exp/save", method = RequestMethod.POST)
    public Experiences saveExperiences( HttpServletRequest request, @RequestBody ExperiencesResponseDto exp) {
        String user_id =  request.getHeader("user_id");
        exp.setUser(us.findById(user_id));
        return ex.save(exp);
    }

    @ApiOperation("경험 정보 1개 삭제")
    @RequestMapping(value = "/exp/del/{id}", method = RequestMethod.DELETE)
    public void deleteExperiences(@PathVariable String id) {
        Long aid = Long.parseLong(id);
        ex.delete(aid);
    }

    @ApiOperation("경험 정보 수정")
    @RequestMapping(value = "/exp/update", method = RequestMethod.POST)
    public Experiences updateExperiences(HttpServletRequest request, @RequestBody ExperiencesResponseDto exp) {
        String user_id =  request.getHeader("user_id");
        exp.setUser(us.findById(user_id));
        return ex.update(ex.findById(exp.getId()), exp.toEntity());
    }

    @ApiOperation("자격내역 조회")
    @RequestMapping(value = "/lic", method = RequestMethod.GET)
    public List<Licenses> findAllLicenses(HttpServletRequest request) {
        String user_id =  request.getHeader("user_id");
        return ls.findAll(us.findById(user_id));
    }

    @ApiOperation("자격 정보 저장")
    @RequestMapping(value = "/lic/save", method = RequestMethod.POST)
    public Licenses saveLicenses(HttpServletRequest request, @RequestBody LicensesResponseDto lic) {
        String user_id =  request.getHeader("user_id");
        lic.setUser(us.findById(user_id));
        return ls.save(lic);
    }

    @ApiOperation("자격 정보 1개 삭제")
    @RequestMapping(value = "/lic/del/{id}", method = RequestMethod.DELETE)
    public void deleteLicense(@PathVariable String id) {
        Long aid = Long.parseLong(id);
        ls.delete(aid);
    }

    @ApiOperation("자격 정보 수정")
    @RequestMapping(value = "/lic/update", method = RequestMethod.POST)
    public Licenses updateLicenses(HttpServletRequest request, @RequestBody LicensesResponseDto lic) {
        String user_id =  request.getHeader("user_id");
        lic.setUser(us.findById(user_id));
        return ls.save(lic);
    }
}
