import {
  ActivatedRouteSnapshot,
  ResolveFn,
} from '@angular/router';

export const resolveTitle: ResolveFn<string> = (
  route: ActivatedRouteSnapshot
) => Promise.resolve(`MyActivePal - ${route.paramMap.get('title')!}`);
