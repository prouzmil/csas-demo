import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { PagedResponse } from "./models/paged-response";
import { Branch } from "./models/branch";
import { environment } from '../environments/environment';
import { BehaviorSubject, finalize, Observable } from "rxjs";
import { Pagination } from "./models/pagination";

/** Sluzba pro nacteni dat z API */
@Injectable()
export class DemoService {
  private apiUrl = environment.apiUrl;
  private branches$ = new BehaviorSubject<Branch[]>([]);
  private pagination$ = new BehaviorSubject<Pagination | null>(null);
  private loading$ = new BehaviorSubject<boolean>(false);

  constructor(private httpClient: HttpClient) {
  }

  /** @return zda probiha nacitani dat */
  public isLoading(): Observable<boolean> {
    return this.loading$.asObservable();
  }

  /** @return seznam pobocek */
  public getBranches(): Observable<Branch[]> {
    return this.branches$.asObservable();
  }

  /** @return informace o strankovani */
  public getPagination(): Observable<Pagination | null> {
    return this.pagination$.asObservable();
  }

  /**
   * Nacteni dat z REST API
   * @param page cislo stranky
   * @param size velikost stranky
   */
  public loadData(page: number = 1, size: number = 15): void {
    this.loading$.next(true);
    this.httpClient.get<PagedResponse<Branch>>(`${this.apiUrl}/branches`, { params: { page, size } })
      .pipe(
        finalize(() => this.loading$.next(false))
      )
      .subscribe({
        next: (response) => {
          this.pagination$.next(response.pagination);
          this.branches$.next(response.items);
        },
        error: (error) => {
          alert('Chyba při načítání dat.');
          console.error(error);
        }
      });
  }
}
