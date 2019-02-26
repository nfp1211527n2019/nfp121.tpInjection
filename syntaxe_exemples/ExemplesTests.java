package syntaxe_exemples;

import container.ApplicationContext;

import java.util.*;

public class ExemplesTests extends junit.framework.TestCase{
    public void testAvecInjection() throws Exception{
        ApplicationContext ctx = container.Factory.createApplicationContext("./syntaxe_exemples/README.TXT");
        A a = ctx.getBean("a");
        assertTrue(a.getI() instanceof B);

        Table table = ctx.getBean("uneTable");
        assertEquals(2, table.taille());
        
        Factory fabrique = ctx.getBean("fabrique");
        Collection<Object> c = fabrique.getInstance();
        c.add(3);c.add(3);
        assertEquals(1, c.size());
        
        ListeDeTables listeDeTables = ctx.getBean("listeDeTables");
        assertEquals(4, listeDeTables.taille());
        
    }

    public void testSansInjection() throws Exception{
        I i = new B();
        A a = new A();
        a.setI(i);

        Table table = new Table();     
        table.setCollection(new HashSet<Integer>());
        table.setInt(3);
        table.setInt(4);
        table.setInt(3);
        assertEquals(2, table.taille());
        
        // table.setCollection(new ArrayList<Integer>());
        // table.setInt(3);
        // table.setInt(4);
        // table.setInt(3);
        // assertEquals(3, table.taille());
        
        Factory fabrique = new Factory();
        fabrique.setInstance(java.util.HashSet.class);
        Collection<Object> c = fabrique.getInstance();
        c.add(3);c.add(3);
        assertEquals(1, c.size());
        
        ListeDeTables listeDeTables = new ListeDeTables();
        listeDeTables.setTable(table);
        listeDeTables.setTables(new Table[]{table,table,table});
        assertEquals(4, listeDeTables.taille());
        
    }
}

