import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sortPipe'
})
export class SortPipe implements PipeTransform {

  transform(items: any[]): any[] {
    return items.sort((a, b) => {
        let aLC: string = a.centreName.toLowerCase();
        let bLC: string = b.centreName.toLowerCase();
        return aLC < bLC ? -1 : (aLC > bLC ? 1 : 0);
    });
  }

}
