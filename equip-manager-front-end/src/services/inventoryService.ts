import axiosClient from "@/config/axiosClient";
import { ItemInventarioDto } from "@/types/item.types";
import { InventoryItem, InventoryResponse } from "@/types/pagination.types";

export const fetchInventoryItems = async (descricao:string, pagina:number): Promise<InventoryResponse> => {
    try {
        const response = await axiosClient.get<InventoryResponse>('/item', {
            params: {
              descricao,
              pagina,
            },
          });
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar itens do inventário:', error);
      throw error;
    }
};

export const salvarNovoItem = async(itemInventarioDto:ItemInventarioDto): Promise<any> => {
  console.log(itemInventarioDto);
  try {
    const response = await axiosClient.post<InventoryItem>('/item', itemInventarioDto);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
}

export default interface ResponseError {
  mensage:string
}
