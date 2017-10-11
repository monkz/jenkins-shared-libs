/*
Author: Malte Kuhn
This script works with workflow to cancel older running builds for the same job after a milestone
Use case: many build may go to QA, but only the build that is accepted is needed,
the other builds in the workflow should be aborted
*/
def call(Integer ordinal){
	def jobname = env.JOB_NAME
	def thisbuildnum = env.BUILD_NUMBER.toInteger()
	
	def job = Jenkins.instance.getItemByFullName(jobname)
	def milestones = Jenkins.getActiveInstance().getDescriptorOrDie(MilestoneStep.class).getMilestonesByOrdinalByJob().get(jobname)
	def buildnums = milestones.get(milestoneordinal)
	for(buildnum in buildnums){
			if (buildnum >= thisbuildnum) { continue; println "equals or newer" }
			Jenkins.instance.getItemByFullName(jobname).getBuildByNumber(86).doStop()
	}
}
