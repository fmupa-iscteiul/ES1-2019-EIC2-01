


public class App {
	
	public App() {
		File file = new File();
		Avaliar_Defeitos av = new Avaliar_Defeitos();
		Criar_Regra c = new Criar_Regra();
		Window.getInstance().addTagPainel(file.getName(), file.getPanel());
		Window.getInstance().addTagPainel(av.getName(), av.getPanel());
		Window.getInstance().addTagPainel(c.getName(), c.getPanel());
	}
	
	private void start() {
		Window.getInstance().open();
	}
	
	
	 public static void main(String[] args) {
		App app = new App();
		app.start();
		}
}
