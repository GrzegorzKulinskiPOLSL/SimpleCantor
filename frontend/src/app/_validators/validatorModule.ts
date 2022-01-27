import {AbstractControl,  ValidatorFn} from '@angular/forms';

export class MyValidator {

  static isInList(currencies: Array<any>): ValidatorFn {
    if ( currencies !== undefined ) {
      return (control: AbstractControl): {} => {
        if (control.value && currencies.some((item) => item.currencyCode === control.value)) {
          return {isInList: true};
        }
        return false;
      };
    }
    return;
  }
}
