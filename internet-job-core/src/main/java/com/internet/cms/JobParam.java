package com.internet.cms;

public class JobParam {

	public static String REF = "CmsTask";
	public static String PROP_USER = "user";
	public static String PROP_JOB_CLASS = "jobClass";
	public static String PROP_SITE = "site";
	public static String PROP_TYPE = "type";
	public static String PROP_INTERVAL_MINUTE = "intervalMinute";
	public static String PROP_TASK_CODE = "taskCode";
	public static String PROP_EXECYCLE = "execycle";
	public static String PROP_CRON_EXPRESSION = "cronExpression";
	public static String PROP_INTERVAL_HOUR = "intervalHour";
	public static String PROP_INTERVAL_UNIT = "intervalUnit";
	public static String PROP_DAY_OF_WEEK = "dayOfWeek";
	public static String PROP_NAME = "name";
	public static String PROP_DAY_OF_MONTH = "dayOfMonth";
	public static String PROP_HOUR = "hour";
	public static String PROP_ENABLE = "enable";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_MINUTE = "minute";
	public static String PROP_ID = "id";
	public static String PROP_REMARK = "remark";
	/**
	 * 任务参数
	 */
	public static String PARAM_JOB = "ssxt_job";
	public static String PARAM_WORK = "ssxt_job";

	/**
	 * 任务执行状态
	 */
	public static int RUN_STATUS_WAIT= 0;
	/**
	 * 任务执行状态
	 */
	public static int RUN_STATUS_GOING = 1;
	/**
	 * 任务执行状态
	 */
	public static int RUN_STATUS_TRY_OVER = 2;
	/**
	 * 任务执行状态
	 */
	public static int RUN_STATUS_TRY_OVER_OK = 3;
	/**
	 * 任务执行状态
	 */
	public static int RUN_STATUS_TRY_OVER_ERROR = 4;
	

	/**
	 * 任务执行状态
	 */
	public static int WORK_STATUS_OK= 0;
	/**
	 * 任务执行状态
	 */
	public static int WORK_STATUS_GOING = 1;
	/**
	 * 任务执行状态
	 */
	public static int WORK_STATUS_TRY_OVER = 2;
	/**
	 * 任务执行状态
	 */
	public static int WORK_STATUS_TRY_OVER_OK = 3;
	/**
	 * 任务执行状态
	 */
	public static int WORK_STATUS_ERROR = 4;
	
	/**
	 * 任务执行周期cron表达式
	 */
	public static int EXECYCLE_CRON = 2;
	/**
	 * 任务执行周期自定义
	 */
	public static int EXECYCLE_DEFINE = 1;
	/**
	 * 执行周期-秒
	 */
	public static int EXECYCLE_SECOND=0;
	/**
	 * 执行周期-分钟
	 */
	public static int EXECYCLE_MINUTE = 1;
	/**
	 * 执行周期-小时
	 */
	public static int EXECYCLE_HOUR = 2;
	/**
	 * 执行周期-日
	 */
	public static int EXECYCLE_DAY = 3;
	/**
	 * 执行周期-月
	 */
	public static int EXECYCLE_WEEK = 4;
	/**
	 * 执行周期-月
	 */
	public static int EXECYCLE_MONTH = 5;
	/**
	 * 首页静态任务
	 */
	public static int TASK_STATIC_INDEX = 1;
	/**
	 * 栏目页静态化任务
	 */
	public static int TASK_STATIC_CHANNEL = 2;
	/**
	 * 内容页静态化任务
	 */
	public static int TASK_STATIC_CONTENT = 3;
	/**
	 * 采集类任务
	 */
	public static int TASK_ACQU = 4;
	/**
	 * 分发类任务
	 */
	public static int TASK_DISTRIBUTE = 5;
	/**
	 * 采集源ID
	 */
	public static String TASK_PARAM_ACQU_ID = "acqu_id";
	/**
	 * 分发FTP ID
	 */
	public static String TASK_PARAM_FTP_ID = "ftp_id";
	/**
	 * 站点 ID
	 */
	public static String TASK_PARAM_SITE_ID = "site_id";
	/**
	 * 栏目 ID
	 */
	public static String TASK_PARAM_CHANNEL_ID = "channel_id";
}
