/*-
 * Copyright (c) 2017
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tr_esor.checktool.validation.default_impl.basis.ers;

import de.bund.bsi.tr_esor.checktool.data.ArchiveTimeStampChain;
import de.bund.bsi.tr_esor.checktool.validation.default_impl.ArchiveTimeStampChainValidator;
import de.bund.bsi.tr_esor.checktool.validation.report.ATSChainReport;
import de.bund.bsi.tr_esor.checktool.validation.report.Reference;


/**
 * Basis-ERS-Profil requires the ArchiveTimeStampChain to contain at least one ArchiveTimeStamp.
 *
 * @author MO
 */
public class BasisErsArchiveTimeStampChainValidator extends ArchiveTimeStampChainValidator
{

  @Override
  protected ATSChainReport validateInternal(Reference ref, ArchiveTimeStampChain toCheck)
  {
    if (toCheck.isEmpty())
    {
      ctx.getFormatOk().invalidate("must contain at least one ArchiveTimeStamp", ref.newChild("numberATS"));
    }
    return super.validateInternal(ref, toCheck);
  }
}
