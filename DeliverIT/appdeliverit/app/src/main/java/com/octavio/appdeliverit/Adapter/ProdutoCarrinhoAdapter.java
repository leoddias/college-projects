package com.octavio.appdeliverit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.octavio.appdeliverit.Model.Produto;
import com.sourcey.appdeliverit.R;

import java.util.ArrayList;


/**
 * Created by Octavio Sabatine Ranieri on 03/04/16.
 */
public class ProdutoCarrinhoAdapter extends ArrayAdapter<Produto> {
    private Context context;
    private ArrayList<Produto> lista;
    private LayoutInflater inflater;

    public ProdutoCarrinhoAdapter(Context context, ArrayList<Produto> lista){
        super(context,0,lista);
        this.context = context;
        this.lista = lista;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final Produto itemPosicao = this.lista.get(position);

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_lista_prod_carrinho,null);
        final View layout = convertView;

        TextView textView = (TextView) convertView.findViewById(R.id.textViewNomeProd);
        //ImageView imageView = (ImageView) convertView.findViewById(R.id.imagem);
        textView.setText(itemPosicao.getNomeProduto()+"       R$ "+itemPosicao.getPreco());
  /*      URL url = null;
        try {
            url = new URL(itemPosicao.getUrlMobile().toString());

            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imageView.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        return convertView;
    }
}
