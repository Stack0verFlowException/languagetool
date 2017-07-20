/* LanguageTool, a natural language style checker
 * Copyright (C) 2012 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.language;

import org.languagetool.rules.Rule;
import org.languagetool.rules.de.GermanSpellerRule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GermanyGerman extends German {

  private File additionalSpellingFile;
  private List<String> additionalWords;


  public GermanyGerman() {
    additionalSpellingFile = null;
  }

  public GermanyGerman( File additionalSpellingFile) {
    this.additionalSpellingFile = additionalSpellingFile;
  }

  public GermanyGerman(List<String> additionalWords) {
    this.additionalWords = additionalWords;
  }

  @Override
  public String[] getCountries() {
    return new String[]{"DE"};
  }

  @Override
  public String getName() {
    return "German (Germany)";
  }

  @Override
  public List<Rule> getRelevantRules(ResourceBundle messages) throws IOException {
    List<Rule> rules = new ArrayList<>(super.getRelevantRules(messages));
    if (additionalSpellingFile == null && additionalWords == null){
      rules.add(new GermanSpellerRule(messages, this));
    }else if (additionalSpellingFile != null && additionalWords == null){
      rules.add(new GermanSpellerRule(messages, this, additionalSpellingFile));
    }else {
      rules.add(new GermanSpellerRule(messages, this, additionalWords));
    }
    return rules;
  }
  
}
