package com.oneconnect.avssolution.OneConnectAdapter;




import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.oneconnect.avssolution.OneConnectActivities.MainActivity;
import com.oneconnect.avssolution.OneConnectFragments.OneConnectPro;
import com.oneconnect.avssolution.R;
import com.oneconnect.avssolution.OneConnectModel.Countries;
import java.util.ArrayList;
import java.util.List;

public class ServerProAdapter extends RecyclerView.Adapter<ServerProAdapter.mViewhoder> {

    ArrayList<Countries> datalist = new ArrayList<>();

    private final Context context;
    private final int AD_TYPE = 0;
    private final int CONTENT_TYPE = 1;
    public ServerProAdapter(Context ctx) {
        this.context=ctx;
    }

    @NonNull
    @Override
    public ServerProAdapter.mViewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.pro_item, parent, false);
        return new ServerProAdapter.mViewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ServerProAdapter.mViewhoder holder, int position) {
        if(getItemViewType(position) == CONTENT_TYPE){
            Countries data = datalist.get(position);
            holder.app_name.setText(data.getCountry());

            Glide.with(context)
                    .load(data.getFlagUrl())
                    .into(holder.flag);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, MainActivity.class);
                    intent.putExtra("c", data);
                    intent.putExtra("type",MainActivity.type);
                    intent.putExtra("admob_banner",MainActivity.admob_banner_id);
                    intent.putExtra("admob_interstitial",MainActivity.admob_interstitial_id);
                    intent.putExtra("fb_banner",MainActivity.fb_banner_id);
                    intent.putExtra("fb_interstitial",MainActivity.fb_interstitial_id);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                   // OneConnectPro.onItemClick(data);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    @Override
    public int getItemViewType(int position) {
        return CONTENT_TYPE;
    }

    public static class mViewhoder extends RecyclerView.ViewHolder
    {
        TextView app_name;
        ImageView flag;

        public mViewhoder(View itemView) {
            super(itemView);
            app_name = itemView.findViewById(R.id.region_title);
            flag = itemView.findViewById(R.id.country_flag);
        }
    }

    public interface RegionListAdapterInterface {
        void onCountrySelected(Countries item);
    }
    public void setData(List<Countries> servers) {
        datalist.clear();
        datalist.addAll(servers);
        notifyDataSetChanged();
    }
}
