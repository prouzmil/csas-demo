import { Component, Input } from '@angular/core';
import { Branch } from "../models/branch";

/** Vypis pobocek */
@Component({
  selector: 'branches-list',
  templateUrl: './branches-list.component.html',
  styleUrls: ['./branches-list.component.less']
})
export class BranchesListComponent {
  /** Seznam pobocek */
  @Input()
  public branches: Branch[];

  /**
   * @param branch pobocka
   * @return odkaz na google mapu s planovanim trasy z pobocky do ockovaciho centra
   */
  public getGoogleMapLink(branch: Branch): string {
    const c1 = branch.coordinates;
    const c2 = branch.nearestVaccinationPlace.coordinates;
    return `https://www.google.com/maps/dir/${c1.lat},${c1.lng}/${c2.lat},${c2.lng}`;
  }
}
