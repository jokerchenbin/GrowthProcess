package com.cst.growthprocess.recyclerViewDemo.inter;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：recyclerView 的item点击事件和长按事件
 *
 * @author chenb
 *
 *         Time: 2016/12/5 0005
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public interface OnItemListener {

    void OnItemClickListener(int position);

    void OnItemLongClickListener(int position);
}
