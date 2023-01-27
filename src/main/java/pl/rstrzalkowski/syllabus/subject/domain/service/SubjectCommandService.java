package pl.rstrzalkowski.syllabus.subject.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.subject.application.command.ArchiveSubjectCommand;
import pl.rstrzalkowski.syllabus.subject.application.command.CreateSubjectCommand;
import pl.rstrzalkowski.syllabus.subject.application.command.UpdateSubjectCommand;
import pl.rstrzalkowski.syllabus.subject.domain.exception.SubjectNotFoundException;
import pl.rstrzalkowski.syllabus.subject.domain.exception.SubjectUpdateException;
import pl.rstrzalkowski.syllabus.subject.domain.model.Subject;
import pl.rstrzalkowski.syllabus.subject.domain.repository.SubjectRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectCommandService {

    private final SubjectRepository subjectRepository;


    public void create(CreateSubjectCommand command) {
        Subject subject = new Subject();
        subject.setName(command.getName());
        subject.setAbbreviation(command.getAbbreviation());

        subjectRepository.save(subject);
    }


    public void archiveById(ArchiveSubjectCommand command) {
        Subject subject = subjectRepository.findById(command.id())
                .orElseThrow(SubjectNotFoundException::new);

        if (subject.isArchived()) {
            return;
        }
        subject.setArchived(true);
        subjectRepository.save(subject);
    }


    public void update(UpdateSubjectCommand command) {
        Subject subject = subjectRepository.findById(command.getId())
                .orElseThrow(SubjectNotFoundException::new);

        subject.setName(command.getName() == null ? subject.getName() : command.getName());
        subject.setAbbreviation(command.getAbbreviation() == null ? subject.getAbbreviation() : command.getAbbreviation());

        try {
            subjectRepository.save(subject);
        } catch (Exception e) {
            throw new SubjectUpdateException();
        }
    }
}
