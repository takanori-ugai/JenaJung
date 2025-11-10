/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rootdev.jenajung;

import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.shared.PrefixMapping;
import com.google.common.base.Function;

/**
 *
 * @author pldms
 */
public class Transformers {

    public final static NodeT NODE = new NodeT();
    public final static EdgeT EDGE = new EdgeT();

    private final static String toString(Resource resource) {
        if (resource.isAnon()) return "[]";
        PrefixMapping pmap = resource.getModel();
        String qname = pmap.qnameFor(resource.getURI());
        if (qname != null) return qname;
        return "<" + resource.getURI() + ">";
    }

    public static class NodeT implements Function<RDFNode, String> {

        public String apply(RDFNode input) {
            if (input.isLiteral()) return input.toString();
            else return Transformers.toString((Resource) input);
        }
        
    }

    public static class EdgeT implements Function<Statement, String> {

        public String apply(Statement input) {
            return Transformers.toString(input.getPredicate());
        }

    }
}
