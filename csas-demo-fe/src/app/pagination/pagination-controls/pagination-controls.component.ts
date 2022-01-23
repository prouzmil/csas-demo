import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Pagination } from "../../models/pagination";
import { DemoService } from "../../demo.service";

/**
 * Vyber stranky
 */
@Component({
  selector: 'pagination-controls',
  templateUrl: './pagination-controls.component.html',
  styleUrls: ['./pagination-controls.component.less']
})
export class PaginationControlsComponent {
  /** Pocet stranek zobrazovanych pred a po aktualni strance */
  private pagesOffset: number = 5;
  /** Informace o strankovani */
  @Input()
  public pagination: Pagination;

  constructor(private demoService: DemoService) {
  }

  /** Zda zobrazit odkaz na prvni stranku */
  public get showFirstPage(): boolean {
    const pages = this.pages;
    return pages.length > 0 && pages[0] !== 1;
  }

  /** Zda zobrazit odkaz na posledni stranku */
  public get showLastPage(): boolean {
    const pages = this.pages;
    return pages.length > 0 && pages[pages.length - 1] !== this.pagination.pageCount;
  }

  /** Cisla zobrazovanych odkazu na strany */
  public get pages(): number[] {
    const { pageNumber, pageCount } = this.pagination;
    const pagesBefore = Math.min(pageNumber - 1, this.pagesOffset);
    const pagesAfter = Math.min(pageCount - pageNumber, this.pagesOffset)
    const startPage = Math.max(pageNumber - this.pagesOffset * 2 + pagesAfter, 1);
    const endPage = Math.min(pageNumber + this.pagesOffset * 2 - pagesBefore, pageCount);
    const pages = [];
    for (let page = startPage; page <= endPage; page++) {
      pages.push(page);
    }
    return pages;
  }

  /**
   * Vybrani stranky
   * @param page cislo stranky
   */
  public selectPage(page: number): void {
    this.demoService.loadData(page);
  }
}
