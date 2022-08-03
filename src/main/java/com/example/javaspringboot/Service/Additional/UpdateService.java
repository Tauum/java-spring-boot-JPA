package com.example.javaspringboot.Service.Additional;

import com.example.javaspringboot.Model.Additional.Update;
import com.example.javaspringboot.Repo.Additional.UpdateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class UpdateService {
    private final com.example.javaspringboot.Repo.Additional.UpdateRepo UpdateRepo;

    @Autowired
    public UpdateService(UpdateRepo UpdateRepository) {
        this.UpdateRepo = UpdateRepository;
    }

    public Update addUpdate(Update Update) { return UpdateRepo.save(Update); }

    public List<Update> findAll(){ return UpdateRepo.findAll(); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteUpdate(Long id) { UpdateRepo.deleteUpdateById(id);}

    public Update findUpdateById(Long id) {
        return UpdateRepo.findUpdateById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id)) ;
    }

    public List<Update> findAllOrderByDate() {
        return UpdateRepo.findAllByOrderByGeneratedDate();
    }

    public List<Update> findAllRecentOrderByDate(LocalDate fromDate) {

        return UpdateRepo.findAllByGeneratedDateGreaterThanOrderByGeneratedDate(fromDate);
//        return UpdateRepo.findAllByOrderByGeneratedDate();
    }

    public Update updateUpdate(Update attempt) {
        {
            return UpdateRepo.save(attempt);
        }
    }
}