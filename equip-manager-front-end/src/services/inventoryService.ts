import axiosClient from "@/config/axiosClient";
import { ItemInventarioDto } from "@/types/item.types";
import { InventoryItem, InventoryResponse } from "@/types/pagination.types";

export const fetchInventoryItems = async (
  descricao: string,
  pagina: number
): Promise<InventoryResponse> => {
  const response = await axiosClient.get<InventoryResponse>("/item", {
    params: {
      descricao,
      pagina,
    },
  });
  return response.data;
};

export const salvarNovoItem = async (
  itemInventarioDto: ItemInventarioDto
): Promise<any> => {
  console.log(itemInventarioDto);
  const response = await axiosClient.post<InventoryItem>(
    "/item",
    itemInventarioDto
  );
  return response.data;
};

export default interface ResponseError {
  mensage: string;
}

export const deleteItemPeloNumeroDeSerie = async (
  numeroDeSerie: String | undefined
): Promise<any> => {
  const response = await axiosClient.delete<InventoryItem>(
    `/item/${numeroDeSerie}`
  );
  return response.data;
};

export const fetchItemByNumeroDeSerie = async (
  numeroDeSerie: String | undefined
): Promise<InventoryItem> => {
  const response = await axiosClient.get<InventoryItem>(
    `/item/${numeroDeSerie}`
  );
  return response.data;
};

export const putItemByNumeroDeSerie = async (
  numeroDeSerie: String | undefined,
  itemInventarioDto: InventoryItem | null
): Promise<InventoryItem> => {
  const response = await axiosClient.put<InventoryItem>(
    "/item",
    itemInventarioDto,
    {
      params: {
        numeroDeSerie,
      },
    }
  );
  return response.data;
};
