package cz.csas.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class DemoRestController {

    private final DemoService demoService;

    public DemoRestController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/branches")
    public PagedResponse<Branch> getBranches(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return demoService.getBranches(page, size);
    }
}
