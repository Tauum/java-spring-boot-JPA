package com.example.javaspringboot.Games.Service;


import com.example.javaspringboot.Games.Model.Answer;
import com.example.javaspringboot.Games.Model.Propagate;
import com.example.javaspringboot.Games.Model.Question;
import com.example.javaspringboot.Games.Model.Quiz;
import com.example.javaspringboot.Games.Repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    private final QuizRepo quizRepo;

    @Autowired
    public QuizService(QuizRepo quizRepository) {
        this.quizRepo = quizRepository;
    }

    public Quiz addQuiz(Quiz quiz)
    {
        return quizRepo.save(quiz);
    }

    public List<Quiz> findAll(){ return quizRepo.findAll(); }

    public Quiz updateQuiz(Quiz quiz){ return quizRepo.save(quiz); }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteQuiz(Long id) { quizRepo.deleteQuizById(id);}

    public Quiz updateQuiz(Long id, Quiz attempt) {
        Quiz find = findQuizById(id);
        if (find != null) {
            find.setGeneral(attempt.getTitle(),attempt.getTimeLimit(),attempt.getValue(),attempt.isHidden(),attempt.getSubject());

            ArrayList<Question> remQ = new ArrayList<>();
            ArrayList<Question> addQ = new ArrayList<>();

            // this is for updating questions
            for(Question attemptCurQ: attempt.questions){
                for (Question findCurQ : find.questions){
                        if (attemptCurQ.getId()!= null ) {
                        if (attemptCurQ.getId().equals(findCurQ.getId())) {
                            findCurQ.setGeneral(attemptCurQ.getQuestion(), attemptCurQ.getExplaination(), attemptCurQ.getValue());

                            // this is for updating answers for each question
                            ArrayList<Answer> remA = new ArrayList<>();
                            ArrayList<Answer> addA = new ArrayList<>();
                          for (Answer findCurA : findCurQ.answers) {
                                boolean aExists = false;
                             for (Answer attemptCurA : attemptCurQ.answers) {
                                    if (attemptCurA.getId() != null) {
                                        if (findCurA.getId().equals(attemptCurA.getId())) {
                                            aExists = true;
                                            findCurA.setGeneral(attemptCurA.getContent(), attemptCurA.getCorrect());
                                        }
                                    }
                                    // this is for adding answers
                                    else{ if (!addA.contains(attemptCurA)) {addA.add(attemptCurA); } } // double condition to prevent being added twice
                                }
                             //this is for removing answers
                                if (!aExists && !remA.contains(findCurA)){ remA.add(findCurA); } // double condition to prevent being added twice

                            }
                            findCurQ.answers.removeAll(remA); // this condition not being met when an answer is removed
                            findCurQ.answers.addAll(addA);
                        }
                    }
                    else{ if (!addQ.contains(attemptCurQ)) { addQ.add(attemptCurQ); } } // double condition to prevent being added twice
                }
            }
            // this is for removing a question that no longer exists
            for(Question findCurQ: find.questions ){
                boolean qExists = false;
                for (Question attemptCurQ : attempt.questions){
                    if (attemptCurQ.getId() == null) {  qExists = true; }
                    else{  if (findCurQ.getId().equals(attemptCurQ.getId())) { qExists = true; }  } // double condition to prevent being added twice
                }
                if (!qExists && !remQ.contains(findCurQ)) { remQ.add(findCurQ); } // double condition to prevent being added twice
            }

            find.questions.addAll(addQ);
            find.questions.removeAll(remQ);
            quizRepo.save(find);
        }
        return find;
    }



    public Quiz findQuizById(Long id)
    {
        Quiz find = quizRepo.findQuizById(id);
        if (find != null){return find;}
        return null;
    }

    public List<Quiz> findAllOrderByGeneratedDateDesc() {
        return quizRepo.findAllByOrderByGeneratedDateDesc();
    }

    public List<Quiz> findAllOrderByGeneratedDateDescAndNotHidden() {
        return quizRepo.findAllByHiddenOrderByGeneratedDateDesc(false);
    }

    public Quiz findQuizOrderByGeneratedDateDescNotHidden() {
        return quizRepo.findFirstByHiddenOrderByIdDesc(false);
    }


}

