import { Component, OnInit } from '@angular/core';
import { Target } from '../models/target.model';
import { TargetServiceService } from './target-service.service';
import { Validators, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-target-view-prime',
  templateUrl: './target-view-prime.component.html',
  styleUrls: ['./target-view-prime.component.css']
})
export class TargetViewPrimeComponent implements OnInit {

  // Table
  targets: Target[];

  // Edit Dialog
  displayTargetDialog: boolean = false;
  targetToEdit: Target = new Target();
  userForm: FormGroup;

  saveTarget() {
    if (this.targetToEdit.id === undefined) {
      this.targetService.newTarget().subscribe(target => {
        this.targetToEdit.id = target.id;
        this.targetToEdit.createdOn = target.createdOn;
        this.targetService.updateTarget(this.targetToEdit).subscribe(() => {
          this.loadTargets();
          this.displayTargetDialog = false;
        });
      })
    } else {
      this.targetService.updateTarget(this.targetToEdit).subscribe(() => {
        this.loadTargets();
        this.displayTargetDialog = false;
      });
    }


  }

  cancelTargetDialog() {
    this.displayTargetDialog = false;
  }

  constructor(private targetService: TargetServiceService, private fb: FormBuilder, private confirmationService: ConfirmationService) { }

  newTarget() {
    this.targetToEdit = new Target();
    this.userForm.get('active').setValue(false);
    this.displayTargetDialog = true;
  }

  editTarget(target: Target) {
    this.targetToEdit = new Target(target);
    this.displayTargetDialog = true;
  }

  deleteTarget(target: Target) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this target? ' + target.name,
      accept: () => {
        this.targetService.deleteTarget(target).subscribe(() => this.loadTargets());
        
      }
    })
  }


  private loadTargets() {
    this.targetService.getTargets().subscribe(data => this.targets = data);
  }

  ngOnInit() {
    this.loadTargets();
    this.userForm = this.fb.group({
      'lotIdInput': new FormControl('', Validators.required),
      "active": new FormControl('', Validators.nullValidator),
      'createdBy': new FormControl('', Validators.required),
      'name': new FormControl('', Validators.required),
      'cuPer': new FormControl('', [Validators.pattern('[-+]?[0-9]*\\.?[0-9]+'), Validators.max(100), Validators.min(0), Validators.required]),
      'inPer': new FormControl('', [Validators.pattern('[-+]?[0-9]*\\.?[0-9]+'), Validators.max(100), Validators.min(0), Validators.required]),
      'gaPer': new FormControl('', [Validators.pattern('[-+]?[0-9]*\\.?[0-9]+'), Validators.max(100), Validators.min(0), Validators.required]),
      'crPer': new FormControl('', [Validators.pattern('[-+]?[0-9]*\\.?[0-9]+'), Validators.max(100), Validators.min(0), Validators.required]),
      'work': new FormControl('', [Validators.pattern('[-+]?[0-9]*\\.?[0-9]+'), Validators.min(0), Validators.required])
    });

  }

}
