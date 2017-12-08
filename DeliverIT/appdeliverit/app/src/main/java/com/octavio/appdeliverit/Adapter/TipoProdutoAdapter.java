package com.octavio.appdeliverit.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.*;
import android.widget.TextView;

import com.sourcey.appdeliverit.R;
import com.octavio.appdeliverit.Model.TipoProduto;

import java.util.ArrayList;


/**
 * Created by Octavio Sabatine Ranieri on 03/04/16.
 */
public class TipoProdutoAdapter extends ArrayAdapter<TipoProduto> {
    private Context context;
    private static LayoutInflater inflater;
    private ArrayList<TipoProduto> lista;

    public TipoProdutoAdapter(Context context,ArrayList<TipoProduto> lista){
        super(context,R.layout.item_lista_prod,lista);
        this.context = context;
        this.lista = lista;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final TipoProduto itemPosicao = this.lista.get(position);
        if (vi == null)
            vi = inflater.inflate(R.layout.item_lista_tipo_prod, null);
        TextView text = (TextView) vi.findViewById(R.id.textViewNomeTipoProd);
        text.setText(itemPosicao.getTipoProduto());
        return vi;
    }


}
