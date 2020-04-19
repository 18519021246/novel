package com.imnu.service;

import com.daowen.entity.Downrecord;

public interface IDownloadService {

    /***
     * 
     * @param id 资源编号
     * @param result  下载结果
     * @return 构建一个下载记录
     */
	Downrecord buildRecord(int id);
	
	/***
	 * 
	 * @param id
	 * @param result
	 * @return
	 * 下载之后动作
	 */
	int  afteredDownload(int id, boolean result);
	
	
}
