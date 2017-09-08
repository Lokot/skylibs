package ru.skysoftlab.skylibs.quartz;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.openejb.quartz.CronScheduleBuilder;
import org.apache.openejb.quartz.Job;
import org.apache.openejb.quartz.JobBuilder;
import org.apache.openejb.quartz.JobDetail;
import org.apache.openejb.quartz.JobKey;
import org.apache.openejb.quartz.Scheduler;
import org.apache.openejb.quartz.SchedulerException;
import org.apache.openejb.quartz.SimpleScheduleBuilder;
import org.apache.openejb.quartz.SimpleTrigger;
import org.apache.openejb.quartz.Trigger;
import org.apache.openejb.quartz.TriggerBuilder;
import org.apache.openejb.quartz.TriggerKey;
import org.apache.openejb.resource.quartz.QuartzResourceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Контроллер задач.
 * 
 * @author Локтионов А.Г.
 *
 */
// @Startup
// @Singleton
public abstract class JobController {

	private Logger LOG = LoggerFactory.getLogger(JobController.class);

	@Resource(lookup = "java:openejb/Resource/QuartzResourceAdapter")
	private QuartzResourceAdapter ra;

	@Inject
	private CdiJobFactory jobFactory;

	private Scheduler scheduler;

	@PostConstruct
	public void scheduleJobs() {
		scheduler = ra.getScheduler();
		try {
			getScheduler().setJobFactory(jobFactory);
			getScheduler().start();
		} catch (SchedulerException e) {
			LOG.error("Error while creating scheduler", e);
		}
		startJobs();
	}

	/**
	 * Запуск заданий.
	 */
	protected abstract void startJobs();

	@SuppressWarnings("unused")
	private Set<? extends Trigger> newHashSet(Trigger... trigger) {
		Set<Trigger> set = new HashSet<>();
		for (Trigger t : trigger) {
			set.add(t);
		}
		return set;
	}

	@PreDestroy
	public void stopJobs() {
		if (getScheduler() != null) {
			try {
				getScheduler().shutdown(false);
			} catch (SchedulerException e) {
				LOG.error("Error while closing scheduler", e);
			}
		}
	}

	/**
	 * Перезапуск задания.
	 * 
	 * @param key
	 * @param cronString
	 * @throws SchedulerException
	 */
	protected void rescheduleJobNow(TriggerKey key, String cronString) throws SchedulerException {
		Trigger trigger = createCronTrigger(key, cronString, null);
		getScheduler().rescheduleJob(key, trigger);
		LOG.debug("RescheduleJob " + key + " = " + cronString);
	}

	/**
	 * Останавливает задачу.
	 * 
	 * @param key
	 * @throws SchedulerException
	 */
	protected void resumeJobNow(JobKey key) throws SchedulerException {
		getScheduler().resumeJob(key);
		LOG.debug("ResumeJob " + key);
	}

	/**
	 * Ударяет задачу.
	 * 
	 * @param key
	 * @throws SchedulerException
	 */
	protected void deleteJobNow(JobKey key) throws SchedulerException {
		getScheduler().deleteJob(key);
		LOG.debug("DeleteJob " + key);
	}

	/**
	 * Создает триггер запуска задания.
	 * 
	 * @param key
	 * @param cronString
	 * @param start
	 * @return
	 */
	protected Trigger createCronTrigger(TriggerKey key, String cronString, Date start) {
		TriggerBuilder<Trigger> rv = TriggerBuilder.newTrigger().withIdentity(key);
		if (start == null) {
			rv.startNow();
		} else {
			rv.startAt(start);
		}
		rv.withSchedule(CronScheduleBuilder.cronSchedule(cronString));
		return rv.build();
	}

	/**
	 * Задание на будующее с единичным запуском.
	 * 
	 * @param jobClass
	 * @param jobKey
	 * @param tKey
	 * @param startDate
	 */
	protected void createFutureJob(Class<? extends Job> jobClass, String name, String group, Date startDate) {
		try {
			final JobDetail alarmJob = JobBuilder.newJob(jobClass).withIdentity(name, group).build();
			final SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).forJob(alarmJob)
					.startAt(startDate).withSchedule(SimpleScheduleBuilder.simpleSchedule()).build();
			getScheduler().scheduleJob(alarmJob, trigger);
		} catch (SchedulerException e) {
			LOG.error("Error create scan temp job", e);
		}
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

}
