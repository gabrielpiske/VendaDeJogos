/*
    Classe que faz o gerenciamento e criação da imagem com base no valor do carrinho, 
    porém com chave pix ja definidano sistema
 */

package util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class PixUtils {

    public static BufferedImage gerarPixQRCode(String chavePix, double valor) throws Exception {
        String payload = gerarPayloadPix(chavePix, valor);

        // Gerar o QR Code a partir do payload
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(payload, BarcodeFormat.QR_CODE, 300, 300);

        return bitMatrixToImage(bitMatrix);
    }

    private static BufferedImage bitMatrixToImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, (matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB()));
            }
        }
        return image;
    }

    private static String gerarPayloadPix(String chavePix, double valor) {
        String valorFormatado = String.format("%.2f", valor).replace(",", ".");

        // Formata o payload Pix de acordo com os parâmetros fornecidos
        return "000201" + // Indicador de formato de payload
               "010212" + // Informações sobre a conta do comerciante
               "26" + String.format("%02d", chavePix.length() + 4) + // Tamanho da chave + 4
               "0014BR.GOV.BCB.PIX" + // Domínio do Pix
               "01" + String.format("%02d", chavePix.length()) + chavePix + // Chave Pix
               "52040000" + // Código de categoria do comerciante
               "5303986" + // Moeda (986 para BRL)
               "54" + String.format("%02d", valorFormatado.length()) + valorFormatado + // Valor do pagamento
               "5802BR" + // País
               "5925Gabriel Schweder Piske" + // Nome do recebedor
               "6009SAO PAULO" + // Cidade
               "62" + String.format("%02d", 4 + 8 + 4 + 12 + 6 + 10) + // Código do banco (323 Mercado Pago)
               "32300010005303406085-3" + // Dados bancários do recebedor (Banco, Agência e Conta)
               "6304"; // CRC (opcional para este exemplo)
    }
}
