import { Component, OnInit, ChangeDetectorRef, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatPaginator, MatDialog } from '@angular/material';
import { UserService } from '../../user.service';
import { Router } from '@angular/router';
import { User } from '../../user.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

 
  public displayedColumns = ['name', 'email', 'update', 'delete'];
  public dataSource = new MatTableDataSource();

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  itens$: Observable<User[]>;

  constructor(private itemService: UserService,private dialog: MatDialog,
    private changeDetectorRefs: ChangeDetectorRef , private router: Router) {
    this.itens$ = itemService.entities$;

  }

  ngOnInit() {
    this.getItens();
  }

    ngAfterViewInit(): void {
     this.dataSource.sort = this.sort;
     this.dataSource.paginator = this.paginator;
  }

  getItens() {
    this.itemService.getAll().subscribe((res) => {
      this.dataSource.data = res ;
      this.changeDetectorRefs.detectChanges();
    });
  }



  public redirectToDelete = (id: string) => {
    let url: string = `/users/delete/${id}`;
    this.router.navigate([url]);
  }


  public redirectToUpdate = (id: string) => {
    let url: string = `/users/update/${id}`;
    this.router.navigate([url]);
  }

}
