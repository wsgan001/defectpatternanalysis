/**
 * 
 */
package cn.edu.fudan.se.defect.track.file;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import cn.edu.fudan.se.defect.track.constants.BugTrackingConstants;
import cn.edu.fudan.se.defectAnalysis.bean.git.GitCommitInfo;
import cn.edu.fudan.se.defectAnalysis.bean.git.GitSourceFile;
import cn.edu.fudan.se.defectAnalysis.dao.git.GitCommitDao;
import cn.edu.fudan.se.defectAnalysis.dao.git.GitSourceFileDao;

/**
 * @author Lotay
 * 
 */
public class BugInduceFileTracker {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String fileName = "org.eclipse.jdt.core/compiler/org/eclipse/jdt/internal/compiler/lookup/Scope.java";

		// 02d4f1049999a6639faf6572ec12176fbdb0f122
		// report: 2014-03-02 23:29:00
		// fix: 2014-03-04 18:08:17
		// bugId: 429424
		Timestamp bugReportTime = new Timestamp(0);
		bugReportTime.setYear(114);
		bugReportTime.setMonth(2);
		bugReportTime.setDate(2);
		bugReportTime.setHours(23);
		bugReportTime.setMinutes(29);
		bugReportTime.setSeconds(0);
		bugReportTime.setNanos(0);
		System.out.println("bugReportTime:" + bugReportTime);

		BugInduceFileTracker track = new BugInduceFileTracker();
		GitCommitInfo commitInfo = track.bugInduceTrack(bugReportTime, null,
				fileName, null);
		System.out.println("commitInfo:" + commitInfo);
	}

	/**
	 * To track the last commit between bug report.
	 * 
	 * @param bugReportTime
	 * @param fixedBugCommitInfo
	 * @param fileName
	 * @param bugReportFixedCommits
	 *            : to store the commit between bug-report and bug-fix
	 * @return
	 */
	public GitCommitInfo bugInduceTrack(Timestamp bugReportTime,
			GitCommitInfo fixedBugCommitInfo, String fileName,
			Map<String,GitCommitInfo> bugReportFixedCommits) {
		if (bugReportTime == null || fileName == null) {
			return null;
		}
		GitSourceFileDao srcFileDao = new GitSourceFileDao();
		List<GitSourceFile> srcFiles = srcFileDao
				.loadSourceFileByFileName(fileName,BugTrackingConstants.HIBERNATE_CONF_PATH);
		GitCommitDao gitCommitDao = new GitCommitDao();
		GitCommitInfo lastestGitCommitInfo = null;

		bugReportFixedCommits.clear();

		for (GitSourceFile gsf : srcFiles) {
			GitCommitInfo gitCommitInfo = gitCommitDao
					.loadGitCommitInfoByRevisionId(gsf.getRevisionId(),BugTrackingConstants.HIBERNATE_CONF_PATH);
			if (gitCommitInfo != null) {	
				// The commit should be the last commit before the report time
				if (gitCommitInfo.getTime()
						.before(fixedBugCommitInfo.getTime())) {
					if (lastestGitCommitInfo == null
							|| gitCommitInfo.getTime().after(
									lastestGitCommitInfo.getTime())) {
						lastestGitCommitInfo = gitCommitInfo;
					}
					/**
					 * Search the bug list to find the bug between bug-report
					 * and bug-fixed.
					 * */
					if (gitCommitInfo.getTime().after(bugReportTime)) {
						bugReportFixedCommits.put(gitCommitInfo.getRevisionID(),gitCommitInfo);
					}
				}
			}
		}

		return lastestGitCommitInfo;
	}
}
