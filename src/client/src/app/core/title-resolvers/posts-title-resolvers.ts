import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";

export const resolveTitle: ResolveFn<string> = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => Promise.resolve(route.paramMap.get('title')!);