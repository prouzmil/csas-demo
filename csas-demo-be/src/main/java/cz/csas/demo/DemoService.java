package cz.csas.demo;

import cz.csas.demo.common.kdtree.KDTree;
import cz.csas.demo.csasdata.CsasBranch;
import cz.csas.demo.csasdata.CsasDataService;
import cz.csas.demo.csasdata.CsasPagedResponse;
import cz.csas.demo.mzcrdata.MzcrDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoService {
    private final CsasDataService csasPlacesServices;
    private final MzcrDataService mzcrDataService;

    @Autowired
    public DemoService(CsasDataService csasPlacesServices, MzcrDataService mzcrDataService) {
        this.csasPlacesServices = csasPlacesServices;
        this.mzcrDataService = mzcrDataService;
    }

    /**
     * @param page stranka
     * @param size pocet prvku na strance
     * @return stranka vysledku
     */
    public PagedResponse<Branch> getBranches(int page, int size) {
        // nacteni pobocek
        CsasPagedResponse<CsasBranch> csasPagedResponse = this.csasPlacesServices.getBranches(page, size);
        // konverze objektu
        List<Branch> branches = csasPagedResponse.getItems().stream()
                .map(Branch::from)
                .collect(Collectors.toList());

        // nacteni kd stromu s ockovacimi misty
        KDTree<VaccinationPlace> vaccinationPlacesKDTree = mzcrDataService.getVaccinationPlacesKDTree();
        // nalezeni nejblizsich ockovacich mist
        branches.forEach(branch -> {
            branch.setNearestVaccinationPlace(vaccinationPlacesKDTree.findNearest(branch));
        });

        return new PagedResponse<>(
                Pagination.builder()
                        .pageNumber(page)
                        .pageSize(size)
                        .pageCount(csasPagedResponse.getPageCount())
                        .totalItemCount(csasPagedResponse.getTotalItemCount())
                        .build(),
                branches
        );
    }

}
