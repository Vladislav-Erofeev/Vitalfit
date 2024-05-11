package com.example.products.services;

import com.example.products.domain.entities.Ration;
import com.example.products.mappers.PropsMapper;
import com.example.products.repositories.RationRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RationService {
    private final RationRepository rationRepository;

    @Transactional
    public Ration save(Long personId, Ration ration) {
        ration.setPersonId(personId);
        return rationRepository.save(ration);
    }

    public List<Ration> getAll(Long personId, String date) throws ParseException {
        Date startDate = PropsMapper.stringToDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 1);
        return rationRepository.findByPersonIdAndCreated(personId, startDate, calendar.getTime());
    }

    @Transactional
    public Ration editById(Long id, Ration ration) {
        Ration oldRation = getById(id);
        ration.setCreated(oldRation.getCreated());
        ration.setPersonId(oldRation.getPersonId());
        return rationRepository.save(ration);
    }

    @Transactional
    public void deleteById(Long id) {
        rationRepository.deleteById(id);
    }

    private Ration getById(Long id) {
        Optional<Ration> optionalRation = rationRepository.findById(id);
        return optionalRation.orElseThrow(() -> new ObjectNotFoundException(id, "Ration not found"));
    }
}
