import axiosClient from "@/config/axiosClient";
import { InventoryResponse } from "@/types/pagination.types";

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
