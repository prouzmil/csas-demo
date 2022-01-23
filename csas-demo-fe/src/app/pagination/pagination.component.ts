import { Component, Input } from '@angular/core';
import { Pagination } from "../models/pagination";

/**
 * Strankovani
 */
@Component({
  selector: 'pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.less']
})
export class PaginationComponent {
  /** Informace o strankovani */
  @Input()
  public pagination: Pagination | null = null;
}
