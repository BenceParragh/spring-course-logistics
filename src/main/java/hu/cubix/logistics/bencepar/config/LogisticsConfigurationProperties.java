package hu.cubix.logistics.bencepar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "logistics")
@Component
public class LogisticsConfigurationProperties {

	private Delay delay;

	public Delay getDelay() {
		return delay;
	}

	public void setDelay(Delay delay) {
		this.delay = delay;
	}

	public static class Delay {

		private Penalty penalty;

		public Penalty getPenalty() {
			return penalty;
		}

		public void setPenalty(Penalty penalty) {
			this.penalty = penalty;
		}

		public static class Penalty {

			private int penalty30min;
			private int penalty60min;
			private int penalty120min;

			public int getPenalty30min() {
				return penalty30min;
			}

			public void setPenalty30min(int penalty30min) {
				this.penalty30min = penalty30min;
			}

			public int getPenalty60min() {
				return penalty60min;
			}

			public void setPenalty60min(int penalty60min) {
				this.penalty60min = penalty60min;
			}

			public int getPenalty120min() {
				return penalty120min;
			}

			public void setPenalty120min(int penalty120min) {
				this.penalty120min = penalty120min;
			}
		}
	}

}
