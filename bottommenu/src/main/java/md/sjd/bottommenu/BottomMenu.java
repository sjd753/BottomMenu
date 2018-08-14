package md.sjd.bottommenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Sajjad Mistri on 03-04-2017.
 */

public class BottomMenu extends LinearLayout {

    private Context context;

    public BottomMenu(Context context) {
        super(context);
    }

    public BottomMenu(Context context, int orientation) {
        super(context);
        this.context = context;
        setOrientation(orientation);
    }

    public void setMenu(Menu menu, final OnMenuListener menuListener) {
        if (menu == null)
            throw new NullPointerException("Menu can't be null");
        if (menu.size() == 0)
            throw new IllegalArgumentException("Menu must have at least one item");
        for (int i = 0; i < menu.size(); i++) {
            final MenuItem menuItem = menu.getItem(i);
            if (menuItem.isVisible()) {
                View itemView = LayoutInflater.from(context).inflate(getOrientation() == VERTICAL ? R.layout.item_bottom_menu : R.layout.item_bottom_nav, null);
                if (menuItem.getIcon() != null) {
                    ImageView icon = itemView.findViewById(R.id.iv_image);
                    icon.setImageDrawable(menuItem.getIcon());
                }
                TextView tvTitle = itemView.findViewById(R.id.tv_title);
                tvTitle.setText(menuItem.getTitle());

                if (menuListener != null) {
                    itemView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            menuListener.onMenuItemClicked(menuItem.getItemId());
                        }
                    });
                }

                addView(itemView);
            }
        }
    }

    public interface OnMenuListener {
        void onMenuItemClicked(int menuItemId);
    }
}
