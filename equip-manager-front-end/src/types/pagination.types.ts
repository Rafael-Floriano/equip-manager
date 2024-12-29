export interface InventoryItem {
    codigoItem: string;
    numeroDeSerie: string;
    disponibilidade: string;
    status: string;
    descricao: string;
    localizacao: string;
}
  
export interface Sort {
    sorted: boolean;
    empty: boolean;
    unsorted: boolean;
}
  
export interface Pageable {
    pageNumber: number;
    pageSize: number;
    sort: Sort;
    offset: number;
    paged: boolean;
    unpaged: boolean;
}
  
export interface InventoryResponse {
    content: InventoryItem[];
    pageable: Pageable;
    totalElements: number;
    totalPages: number;
    last: boolean;
    size: number;
    number: number;
    sort: Sort;
    numberOfElements: number;
    first: boolean;
    empty: boolean;
}
  