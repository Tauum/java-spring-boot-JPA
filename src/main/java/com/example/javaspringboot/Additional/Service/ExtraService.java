package com.example.javaspringboot.Additional.Service;

import com.example.javaspringboot.Additional.Model.Extra;
import com.example.javaspringboot.Additional.Repository.ExtraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ExtraService {
    private final com.example.javaspringboot.Additional.Repository.ExtraRepo ExtraRepo;

    @Autowired
    public ExtraService(ExtraRepo ExtraRepository) {
        this.ExtraRepo = ExtraRepository;
    }

    public Extra addExtra(Extra Extra) { return ExtraRepo.save(Extra); }

    public List<Extra> findAll(){ return ExtraRepo.findAll(); }

    public Extra updateExtra(Extra Extra){ return ExtraRepo.save(Extra); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteExtra(Long id) { ExtraRepo.deleteExtraById(id);}

//    public void deleteExtra(Long id) {
//        Extras.removeIf
//    }

    public Extra findExtraById(Long id)
    {
        return ExtraRepo.findExtraById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id)) ;


    }

    public List<Extra> findAllOrderByDate(){
        return ExtraRepo.findAllByOrderByGeneratedDateDesc();
    }

    public List<Extra> findAllContainingTitle(String title)
    {
//        return ExtraRepo.findByTitleContainsOrderByGeneratedDateDesc(title);
        return ExtraRepo.findByTitleContainsAndHiddenEqualsOrderByGeneratedDateDesc(title, false);

    }

    public List<Extra> findAllOrderByGeneratedDateDescAndNotHidden() {

        return ExtraRepo.findAllByHiddenOrderByGeneratedDateDesc(false);
    }
}

