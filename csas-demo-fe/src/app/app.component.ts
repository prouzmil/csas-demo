import { Component, OnInit } from '@angular/core';
import { DemoService } from "./demo.service";
import { Observable } from "rxjs";
import { Branch } from "./models/branch";
import { Pagination } from "./models/pagination";

/**
 * Hlavni komponenta aplikace
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less'],
  providers: [DemoService]
})
export class AppComponent implements OnInit {
  constructor(private demoService: DemoService) {
  }

  ngOnInit(): void {
    // nacteni prvni stranky
    this.demoService.loadData(1);
  }

  /** Zda probiha nacitani dat */
  public get loading$(): Observable<boolean> {
    return this.demoService.isLoading();
  }

  /** Seznam pobocek */
  public get branches$(): Observable<Branch[]> {
    return this.demoService.getBranches();
  }

  /** Informace o strankovani */
  public get pagination$(): Observable<Pagination | null> {
    return this.demoService.getPagination();
  }

}
