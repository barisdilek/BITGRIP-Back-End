package de.bitgrip.codingchallenge.service;

import de.bitgrip.codingchallenge.entity.District;
import de.bitgrip.codingchallenge.model.DistrictListModel;
import de.bitgrip.codingchallenge.model.DistrictModel;
import de.bitgrip.codingchallenge.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictService {
    @Autowired
    DistrictRepository districtRepository;

    public List<DistrictListModel> getAll() {
        List<DistrictListModel> districtList = districtRepository
                .findAll()
                .stream()
                .map(x -> new DistrictListModel(x))
                .collect(Collectors.toList());
        return districtList;
    }

    public List<District> getSortedList(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<District> list = districtRepository.findAll(paging);

        if (list.hasContent()) {
            return list.getContent();
        } else {
            return new ArrayList<District>();
        }
    }

    public DistrictModel getById(int districtId) {
        return new DistrictModel(districtRepository.findById(districtId).get());
    }
}
