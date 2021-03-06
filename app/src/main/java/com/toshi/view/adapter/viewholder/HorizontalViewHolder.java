/*
 * 	Copyright (c) 2017. Toshi Browser, Inc
 *
 * 	This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.toshi.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.toshi.R;
import com.toshi.model.local.ToshiEntity;
import com.toshi.util.ImageUtil;
import com.toshi.util.ScreenUtil;
import com.toshi.view.adapter.listeners.OnItemClickListener;
import com.toshi.view.custom.StarRatingView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HorizontalViewHolder<T extends ToshiEntity> extends RecyclerView.ViewHolder {

    private static final double NUMBER_OF_PORTRAIT_APPS = 3.5;
    private static final double NUMBER_OF_LANDSCAPE_APPS = 5.5;
    private static final double MINIMAL_SCREEN_RATIO = 1.4;

    private TextView appLabel;
    private TextView appCategory;
    private StarRatingView ratingView;
    private CircleImageView appImage;

    public HorizontalViewHolder(View itemView) {
        super(itemView);

        this.appLabel = (TextView) itemView.findViewById(R.id.app_label);
        this.appCategory = (TextView) itemView.findViewById(R.id.app_category);
        this.ratingView = (StarRatingView) itemView.findViewById(R.id.rating_view);
        this.appImage = (CircleImageView) itemView.findViewById(R.id.app_image);
    }

    public HorizontalViewHolder setElement(final T elem) {
        setAvatarSize();
        renderName(elem);
        loadImage(elem);
        return this;
    }

    private void renderName(final T elem) {
        this.appLabel.setText(elem.getDisplayName());
        this.ratingView.setStars(elem.getAverageRating());
    }

    private void setAvatarSize() {
        final int avatarSize = getAvatarSize();
        this.appImage.getLayoutParams().width = avatarSize;
        this.appImage.getLayoutParams().height = avatarSize;
        this.appImage.requestLayout();
    }

    private int getAvatarSize() {
        final int screenWidth = ScreenUtil.getWidthOfScreen();
        final int horizontalMargin = this.appImage.getContext().getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        final double numberOfVisibleApps =
                ScreenUtil.isPortrait() || ScreenUtil.getScreenRatio() < MINIMAL_SCREEN_RATIO
                        ? NUMBER_OF_PORTRAIT_APPS
                        : NUMBER_OF_LANDSCAPE_APPS;
        return (int)(screenWidth / numberOfVisibleApps) - horizontalMargin;
    }

    private void loadImage(final T elem) {
        ImageUtil.load(elem.getAvatar(), this.appImage);
    }

    public HorizontalViewHolder setOnClickListener(final OnItemClickListener<ToshiEntity> listener,
                                                   final ToshiEntity elem) {
        this.itemView.setOnClickListener(__ -> listener.onItemClick(elem));
        return this;
    }
}
