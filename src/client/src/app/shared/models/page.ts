export interface Page<T> {
  content: T;
  pageable: {
    number: number;
    size: number;
    sort: any;
  };
  totalSize: number;
}
