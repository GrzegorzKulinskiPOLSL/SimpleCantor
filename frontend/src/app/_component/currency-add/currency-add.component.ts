import {Component, OnInit} from '@angular/core';
import {CurrencyService} from '../../_services/currencyService/currency.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {StoreService} from '../../_services/storeService/store.service';
import {MyValidator} from '../../_validators/validatorModule';

@Component({
  selector: 'app-currency-add',
  templateUrl: './currency-add.component.html',
  styleUrls: ['./currency-add.component.css']
})
export class CurrencyAddComponent implements OnInit {
  submitted = false;
  addCurrencyForm: FormGroup;
  show = false;
  info: string;
  private formBuilder: FormBuilder;

  constructor(private currencyService: CurrencyService, private storeService: StoreService) {
  }

  get f() {
    return this.addCurrencyForm.controls;
  }

  ngOnInit() {
    this.refresh();
  }

  refresh() {

    this.storeService.getData().subscribe(data => {
      this.formBuilder = new FormBuilder();
      this.prepareForm(data);
      this.show = true;
    });


  }

  prepareForm(test: Array<any>) {
    this.addCurrencyForm = this.formBuilder.group({
      currencyCode: ['', [Validators.required,
        Validators.minLength(3),
        Validators.maxLength(3),
        Validators.pattern('[A-Z]*'),
        MyValidator.isInList(test)
      ]],
      name: ['', [Validators.required]]
    });
    this.show = true;
  }

  async onSubmit() {
    this.submitted = true;

    if (this.addCurrencyForm.invalid) {
      return;
    }

    await this.currencyService.addCurrency(this.addCurrencyForm.value).then(data => {
      if (data.status === 200) {
        this.submitted = false;
        this.storeService.refresh().then(() => this.ngOnInit());
        this.info = 'Dodane ';
      } else {
        this.info = 'Błąd ';
      }

      this.info += ' ,http status: ' + data.status + ' ' + data.statusText;
    });

  }
}
