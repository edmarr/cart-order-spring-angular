import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Observable } from 'rxjs';
import { User } from '../../user.model';
import { MatDialog } from '@angular/material';
import { UserService } from '../../user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-delete',
  templateUrl: './user-delete.component.html',
  styleUrls: ['./user-delete.component.css']
})
export class UserDeleteComponent implements OnInit {
  loading$: Observable<boolean>;
  users$: Observable<User[]>;
  public user: User;

  constructor(private location: Location, private dialog: MatDialog,
    private activeRoute: ActivatedRoute, private userService: UserService) {

    this.users$ = userService.entities$;
    this.loading$ = userService.loading$;

  }

  ngOnInit() {
    this.getItemById();
  }
  private getItemById() {
    let id: number = this.activeRoute.snapshot.params['id'];

    this.userService.getByKey(id).subscribe(res => {
      this.user = res as User;
    });
  }

  public delete() {
    this.userService.delete(this.user.id).subscribe(res => {
      this.location.back();
    });
  }

  public onCancel = () => {
    this.location.back();
  }
}