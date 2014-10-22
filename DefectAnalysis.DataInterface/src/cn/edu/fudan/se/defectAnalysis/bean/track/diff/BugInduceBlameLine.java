/**
 * 
 */
package cn.edu.fudan.se.defectAnalysis.bean.track.diff;

/**
 * @author Lotay
 * 
 */
@SuppressWarnings("serial")
public class BugInduceBlameLine implements java.io.Serializable{
	private int bugId;
	private String inducedRevisionId;
	private String fixedRevisionId;
	private String fileName;
	private int inducedlineNumber;

	public int getBugId() {
		return bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public String getInducedRevisionId() {
		return inducedRevisionId;
	}

	public void setInducedRevisionId(String inducedRevisionId) {
		this.inducedRevisionId = inducedRevisionId;
	}

	public String getFixedRevisionId() {
		return fixedRevisionId;
	}

	public void setFixedRevisionId(String fixedRevisionId) {
		this.fixedRevisionId = fixedRevisionId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getInducedlineNumber() {
		return inducedlineNumber;
	}

	public void setInducedlineNumber(int inducedlineNumber) {
		this.inducedlineNumber = inducedlineNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bugId;
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result
				+ ((fixedRevisionId == null) ? 0 : fixedRevisionId.hashCode());
		result = prime
				* result
				+ ((inducedRevisionId == null) ? 0 : inducedRevisionId
						.hashCode());
		result = prime * result + inducedlineNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BugInduceBlameLine)) {
			return false;
		}
		BugInduceBlameLine other = (BugInduceBlameLine) obj;
		if (bugId != other.bugId) {
			return false;
		}
		if (fileName == null) {
			if (other.fileName != null) {
				return false;
			}
		} else if (!fileName.equals(other.fileName)) {
			return false;
		}
		if (fixedRevisionId == null) {
			if (other.fixedRevisionId != null) {
				return false;
			}
		} else if (!fixedRevisionId.equals(other.fixedRevisionId)) {
			return false;
		}
		if (inducedRevisionId == null) {
			if (other.inducedRevisionId != null) {
				return false;
			}
		} else if (!inducedRevisionId.equals(other.inducedRevisionId)) {
			return false;
		}
		if (inducedlineNumber != other.inducedlineNumber) {
			return false;
		}
		return true;
	}
}