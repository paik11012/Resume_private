package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.*;
import com.ssafy.web9to6.dto.ResumeResponseDto;
import com.ssafy.web9to6.dto.TagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {
    @Autowired
    private TagRepository tr;

    //    public List<Tag> findResumesByTagName(String tag_name) {
//        return tr.findByTagName(tag_name);
//    }
    public  Tag save(TagResponseDto lic) {
        return tr.save(lic.toEntity());
    }

    public void delete(Long id) {
        tr.deleteById(id);
    }

    public List<String> findAll(Resume resume) {
        List<Tag> list = tr.findByResume(resume);
        List<String> list2 = new ArrayList<>();

        for(int i=0;i<list.size();i++) {
            list2.add(list.get(i).getTag_name());
        }
        return list2;
    }

    public List<Tag> findAllTag(Resume resume) {
        List<Tag> list = tr.findByResume(resume);

        return list;
    }
}
